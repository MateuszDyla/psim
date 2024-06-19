import React, { useEffect, useState } from "react";
import Header from "../Components/Header";
import Logo from "../Components/Logo";
import CurrentGameBar from "../Components/CurrentGameBar";
import { DarkButton } from "../Components/DarkButton";
import Timer from "../Components/Timer";
import "../styles/GamePageStyle.css";
import { useNavigate } from "react-router-dom";

function GamePage() {
  const width = window.innerWidth;
  const userId = localStorage.getItem("userId");
  const [bar, setBar] = useState(null);
  const [game, setGame] = useState(null);

  const [loading, setLoading] = useState(true);

  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await fetch("http://localhost:8080/game/" + userId);
        const data = await response.json();
        setBar(data.currentBar);
        setGame(data);
      } catch (err) {
        console.log(err);
        try {
          const response = await fetch("http://localhost:8080/bars/getRandom");
          const randomBar = await response.json();
          const responseGame = await fetch("http://localhost:8080/game", {
            method: "POST",
            headers: {
              "Content-Type": "application/json",
            },
            body: JSON.stringify({
              userID: userId,
              barID: randomBar.id,
            }),
          });
          const newGame = await responseGame.json();
          console.log(newGame);
          setGame(newGame);
          if (!bar) {
            setBar(newGame.currentBar);
          }
        } catch (innerErr) {
          console.log(innerErr);
        }
      } finally {
        setLoading(false);
      }
    };

    fetchData();
  }, []);

  const navigate = useNavigate();

  const handleLogout = () => {
    localStorage.removeItem("token");
    localStorage.removeItem("userId");
    navigate("/");
  };

  const endGame = async () => {
    try {
      await fetch("http://localhost:8080/game/" + userId, {
        method: "DELETE",
      });

      if (game.visitedBars > 0) {
        console.log(game.visitedBars);

        let dateDiff = 0;
        if (game && game.startDate) {
          const dateNow = Date();
          const startDate = game.startDate;
          dateDiff = Date.parse(dateNow) - Date.parse(startDate);
          dateDiff = new Date(dateDiff).toISOString().slice(11, 19);
        }

        await fetch("http://localhost:8080/ranking/", {
          method: "POST",
          headers: {
            "Content-Type": "application/json",
          },
          body: JSON.stringify({
            userID: userId,
            visitedBarsNumber: game.visitedBars,
            gameplayTime: dateDiff,
          }),
        });
      }
      // navigate("/main");
    } catch (err) {
      console.log(err);
    }
    navigate("/main");
  };
  if (width > 500) {
    return <div> z kompem raczej nie będziesz biegał alkoholiku</div>;
  }

  let remainingTime = 0;
  if (game && game.finishUntil) {
    const dateUntil = new Date(game.finishUntil);
    const currentDate = new Date();
    remainingTime = dateUntil - currentDate;
    console.log(remainingTime);
  }

  return (
    <div className="background">
      <Header onLogout={handleLogout} />
      <div className="tiny-logo-container">
        <Logo />
      </div>
      {loading ? (
        <div>Loading...</div>
      ) : (
        bar && (
          <CurrentGameBar
            img={bar.imageUrl}
            barName={bar.name}
            barDesc={bar.address}
          />
        )
      )}
      <div className="timer">
        {game && <Timer duration={remainingTime} onTimeout={endGame} />}
      </div>
      <div className="game-bottom">
        <DarkButton onClick={() => navigate("/scanner")}>Zeskanuj</DarkButton>{" "}
        <DarkButton onClick={() => navigate("/main")}>Powrót</DarkButton>
        <DarkButton onClick={endGame}>Zakończ</DarkButton>
      </div>
    </div>
  );
}

export default GamePage;
