import React from 'react';
import '../styles/VisitedBarStyle.css';
import { useNavigate } from 'react-router-dom';

// "Tile" with the name and image of the bar, and the last visit date.
function VisitedBarRectangle({ barImg, barName, barId, date }) {
    const navigate = useNavigate();

    const handleClick = () => {
        navigate(`/bar-details?id=${barId}`);
    };

    return (
        <div id="visited-bar" onClick={handleClick}>
            <p className="bar-name">{barName}</p>
            <p className="bar-date">{date}</p>
            <img src={barImg} alt={`${barName} Image`} />
        </div>
    );
}

export default VisitedBarRectangle;
