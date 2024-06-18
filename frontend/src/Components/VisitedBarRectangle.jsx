import React from 'react';
import '../styles/VisitedBarStyle.css';
import { useNavigate } from 'react-router-dom';

function VisitedBarRectangle({ barImg, barName, barId, description }) {
    const navigate = useNavigate();

    const handleClick = () => {
        navigate(`/bar-details?id=${barId}`);
    };

    return (
        <div id="visited-bar" onClick={handleClick}>
            <p className="bar-name">{barName}</p>
            <img src={barImg} alt={`${barName} Image`} />
        </div>
    );
}

export default VisitedBarRectangle;
