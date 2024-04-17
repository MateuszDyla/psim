//Author: Marta Boryczka

import React, { useState, useEffect } from "react";
import '../styles/TimerStyle.css'


//Countdown timer visible in the game
const Timer = ({duration}) => {
    const [time, setTime] = useState(duration);

    useEffect(() => {
        setTimeout(() => {
            setTime(time - 1000);
        }, 1000);

    }, [time]);

    const getFormattedTime = (milliseconds) => {
        let total_seconds = parseInt(Math.floor(milliseconds / 1000));
        let total_minutes = parseInt(Math.floor(total_seconds / 60));

        let seconds = parseInt(total_seconds % 60);
        let minutes = parseInt(total_minutes % 60);
        return `${minutes}min ${seconds}s`;
    };

    return <div className="timer">{getFormattedTime(time)}</div>;
};

export default Timer;