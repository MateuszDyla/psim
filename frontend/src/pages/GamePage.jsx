import React, { useEffect, useState } from "react";
import Header from "../Components/Header";
import Logo from "../Components/Logo";
import CurrentGameBar from "../Components/CurrentGameBar";
import { DarkButton } from "../Components/DarkButton";
import Timer from "../Components/Timer";
import "../styles/GamePageStyle.css";
import { useNavigate } from "react-router-dom";

function GamePage() {
    const [records, setRecords] = useState(null);
    const [scanMode, setScanMode] = useState(0); // Przenieś scanMode do stanu
    const width = window.innerWidth;

    useEffect(() => {
        async function fetchBar() {
            try {
                const response = await fetch("http://localhost:8080/bars/getRandom");
                const data = await response.json();
                setRecords(data);
            } catch (err) {
                console.log(err);
            }
        }
        fetchBar();
    }, []);

    const navigate = useNavigate();

    const handleLogout = () => {
        localStorage.removeItem("token");
        navigate("/");
    };

    const onScan = (result) => {
        if (result) {
            console.log(result);
            const url = result[0].rawValue;
            console.log(url);
            fetchBar();
        }
    };

    if (width > 500) {
        return <div> z kompem raczej nie będziesz biegał alkoholiku</div>;
    }

    return (
        <div className="background">
            <Header onLogout={handleLogout} />
            <div className="small-logo-container">
                <Logo />
            </div>
            {records && (
                <CurrentGameBar
                    img="https://travel-mates.pl/wp-content/uploads/2022/11/Cybermachina0-1080x1080.jpg"
                    barName={records.name}
                    barDesc={records.address}
                />
            )}
            <div className="timer">
                <Timer duration={30 * 60 * 1000} />
            </div>
            <div className="game-bottom">
                <DarkButton onClick={() => navigate("/scanner")}>Zeskanuj</DarkButton>{" "}
                <DarkButton>Powrót</DarkButton>
            </div>
        </div>
    );
}

export default GamePage;