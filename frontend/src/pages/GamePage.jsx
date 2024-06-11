import React from "react";
import { useNavigate } from "react-router-dom"; // dodaj import
import Header from "../Components/Header";
import Logo from "../Components/Logo";
import CurrentGameBar from "../Components/CurrentGameBar";
import { DarkButton } from "../Components/DarkButton";
import Timer from "../Components/Timer";
import "../styles/GamePageStyle.css";

function GamePage() {
    const navigate = useNavigate(); // dodaj hook useNavigate
    const handleLogout = () => {
        localStorage.removeItem('token');
        navigate('/');
    };

    const width = window.innerWidth;

    if (width > 500) {
        return <div> z kompem raczej nie bedziesz biegal alkoholiku</div>;
    }

    return (
        <div className="background">
            <Header onLogout={handleLogout} />
            <div className="small-logo-container">
                <Logo />
            </div>
            <CurrentGameBar
                img="https://travel-mates.pl/wp-content/uploads/2022/11/Cybermachina0-1080x1080.jpg"
                barName="Cybermachina"
                barDesc="Wrocławska 15"
            />

            <div className="timer">
                <Timer duration={30 * 60 * 1000} />
            </div>
            <div className="bottom">
                <DarkButton>Zeskanuj</DarkButton>
                <DarkButton>Powrót</DarkButton>
            </div>
        </div>
    );
}

export default GamePage;
