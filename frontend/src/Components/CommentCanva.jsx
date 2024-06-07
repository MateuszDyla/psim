// Author: Mateusz Dyla
import React, { useRef, useEffect, useState } from "react";
import "../styles/CommentRectangleStyle.css";

function CommentCanva(props) {
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const [data, setData] = useState([]);
  const canvasRef = useRef(null);

  useEffect(() => {
    setLoading(true);
    fetch("http://localhost:8080/comments/bar/" + props.id)
      .then((response) => {
        if (!response.ok) {
          throw new Error("Network response was not ok");
        }
        return response.json();
      })
      .then((data) => {
        setData(data);
        setLoading(false);
      })
      .catch((err) => {
        setError(err.message);
        setLoading(false);
      });
  }, [props.id]);

  useEffect(() => {
    if (!loading && data.length > 0 && canvasRef.current) {
      const canvas = canvasRef.current;
      const ctx = canvas.getContext("2d");

      // Ustawienia rozmiaru kanwy
      const parentWidth = canvas.parentElement.offsetWidth * 0.8;
      canvas.width = parentWidth;
      canvas.height = 1000; // Wysokość kanwy, aby umożliwić przewijanie

      ctx.fillStyle = "#091d28"; // Ustawienie tła kanwy na ciemny kolor
      ctx.fillRect(0, 0, canvas.width, canvas.height);

      // Funkcja losująca liczbę całkowitą w zakresie [min, max]
      const getRandomInt = (min, max) =>
        Math.floor(Math.random() * (max - min + 1)) + min;

      // Funkcja losująca kolor
      const getRandomColor = () => `hsl(${Math.random() * 360}, 100%, 50%)`;

      // Lista 10 możliwych czcionek
      const fonts = [
        "Arial",
        "Verdana",
        "Helvetica",
        "Times New Roman",
        "Courier New",
        "Georgia",
        "Palatino",
        "Garamond",
        "Bookman",
        "Comic Sans MS",
      ];

      // Rysowanie stringów na kanwie
      data.forEach((str, index) => {
        const fontSize = getRandomInt(20, 50);
        const font = fonts[getRandomInt(1, 9)]; // Losowanie czcionki
        ctx.font = `${fontSize}px ${font}`;
        ctx.fillStyle = getRandomColor();
        const x = getRandomInt(-100, canvas.width);
        const y = -50 + index * 30;
        ctx.save();
        ctx.translate(x, y);
        ctx.rotate((Math.PI / 180) * getRandomInt(-45, 45));
        ctx.fillText(str, 0, 0);
        ctx.restore();
      });
    }
  }, [loading, data]);

  if (loading) {
    return <div>Loading...</div>;
  }

  if (error) {
    return <div>Error: {error}</div>;
  }

  return (
    <div style={{ overflow: "auto", height: "100vh", width: "100%" }}>
      <canvas ref={canvasRef} style={{ border: "1px solid black" }} />
    </div>
  );
}

export default CommentCanva;
