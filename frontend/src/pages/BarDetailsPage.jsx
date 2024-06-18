import React, { useEffect, useState } from "react";
import "../styles/BarDetailsPageStyle.css";
import Header from "../Components/Header";
import Logo from "../Components/Logo";
import CommentCanva from "../Components/CommentCanva";
import { useNavigate, useLocation } from "react-router-dom";

function BarDetailsPage() {
    const navigate = useNavigate();
    const location = useLocation();
    const queryParameters = new URLSearchParams(location.search);
    const id = queryParameters.get("id");
    const userId = localStorage.getItem('userId');
    const token = localStorage.getItem('token');

    const [barInfo, setBarInfo] = useState(null);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);

    const handleLogout = () => {
        localStorage.removeItem('token');
        navigate('/');
    };

    useEffect(() => {
        setLoading(true);
        fetch(`http://localhost:8080/bars/${id}/user/${userId}`, {
            headers: {
                'Authorization': `Bearer ${token}`
            }
        })
            .then((response) => {
                if (!response.ok) {
                    throw new Error(`HTTP error! Status: ${response.status}`);
                }
                return response.json();
            })
            .then((data) => {
                setBarInfo(data);
                setLoading(false);
            })
            .catch((error) => {
                console.error('Error fetching bar details:', error);
                setError(error.message);
                setLoading(false);
            });
    }, [id, userId, token]);

    if (loading) {
        return <div>Loading...</div>;
    }

    if (error) {
        return <div>Error: {error}</div>;
    }

    if (!barInfo) {
        return <div>No bar information available</div>;
    }

    return (
        <>
            <Header onLogout={handleLogout} />
            <div className="logo-container">
                <Logo />
            </div>
            <div id="bar-details">
                <div id="bar-details-image">
                    <img src={barInfo.bar.imageUrl} />
                </div>
                <div id="bar-details-text">
                    <p className="bar-name-profile">{barInfo.bar.name}</p>
                    <p className="bar-address-profile">{barInfo.bar.address}</p>
                    <span>Łącznie odwiedzony {barInfo.allVisits} razy</span>
                    <br />
                    <span>Odwiedzony przez Ciebie {barInfo.visitsByUser} razy</span>
                    <br />
                </div>
            </div>
            <div className="center" width="80%">
                <CommentCanva id="5" />
            </div>
        </>
    );

}

export default BarDetailsPage;
