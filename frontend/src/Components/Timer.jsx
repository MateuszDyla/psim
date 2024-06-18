//Author: Marta Boryczka

import React, { useState, useEffect } from "react";
import "../styles/TimerStyle.css";

//Countdown timer visible in the game
const Timer = ({ duration, onTimeout }) => {
  const [time, setTime] = useState(duration);

  useEffect(() => {
    const timer = setTimeout(() => {
      if (time > 0) {
        setTime(time - 1000);
      } else {
        if (typeof onTimeout === "function") {
          onTimeout();
        }
      }
    }, 1000);
  }, [time, onTimeout]);

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
