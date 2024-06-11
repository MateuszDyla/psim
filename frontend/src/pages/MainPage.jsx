//Author: Mateusz Dyla
import React from 'react';
import { useNavigate } from 'react-router-dom';
import { DarkButton } from '../Components/DarkButton';
import Header from '../Components/Header';
import Logo from '../Components/Logo';
import '../styles/MainPageStyle.css';

function MainPage() {
    const navigate = useNavigate();

    const handleLogout = () => {
        localStorage.removeItem('token');
        navigate('/');
    };

    return (
        <>
            <Header onLogout={handleLogout} />
            <div className="logo-container">
                <Logo />
            </div>
            <br />
            <div id="main-page-buttons">
                <DarkButton onClick={() => navigate('/alkoholizacja')} id="start-adv-button">
                    Zacznij przygodę!
                </DarkButton>
                <DarkButton onClick={() => navigate('/odwiedzone-bary')}>Odwiedzone bary</DarkButton>
                <DarkButton onClick={() => navigate('/ranking')}>Ranking maratonu</DarkButton>
            </div>
        </>
    );
}

export default MainPage;
