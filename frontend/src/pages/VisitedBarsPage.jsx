import React, { useEffect, useState } from "react";
import Header from "../Components/Header";
import Logo from "../Components/Logo";
import { DarkButton } from "../Components/DarkButton";
import { useNavigate } from "react-router-dom";
import VisitedBarRectangle from "../Components/VisitedBarRectangle";
import "../styles/VisitedBarsPageStyle.css";

function VisitedBarsPage() {
  const navigate = useNavigate();
  const [records, setRecords] = useState([]);

  useEffect(() => {
    fetch("http://localhost:8080/user/5/unlocked")
      .then((response) => response.json())
      .then((data) => setRecords(data))
      .catch((err) => console.log(err));
  }, []);

  return (
    <>
      <Header />
      <div className="small-logo-container">
        <Logo />
      </div>
      <div id="visited-bars-placeholder">
        {records.map((record, index) => (
          <VisitedBarRectangle
            barImg={record.imageUrl}
            barName={record.name} // Assuming the record has a 'name' property
            date="24.02.2022"
          />
        ))}
      </div>
      <div className="center">
        <DarkButton onClick={() => navigate("/main")}>Powrót</DarkButton>
      </div>
    </>
  );
}

export default VisitedBarsPage;
