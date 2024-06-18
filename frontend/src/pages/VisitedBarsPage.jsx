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
    const userId = localStorage.getItem('userId');

    useEffect(() => {
        const token = localStorage.getItem('token');

        fetch(`http://localhost:8080/user/${userId}/unlocked`, {
            headers: {
                'Authorization': `Bearer ${token}`
            }
        })
            .then((response) => response.json())
            .then((data) => setRecords(data))
            .catch((err) => console.log(err));
    }, []);

    const handleLogout = () => {
        localStorage.removeItem('token');
        localStorage.removeItem('userId');
        navigate('/');
    };

    return (
        <>
            <Header onLogout={handleLogout} />
            <div className="small-logo-container">
                <Logo />
            </div>
            <div id="visited-bars-placeholder">
                {records.map((record, index) => (
                    <VisitedBarRectangle
                        key={index}
                        barImg={record.imageUrl}
                        barName={record.name}
                        barId={record.id}
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
