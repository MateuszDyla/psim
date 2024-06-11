//Author: Mateusz Dyla

import React from 'react'
import Header from '../Components/Header';
import Logo from '../Components/Logo';
import RankingTable from "../Components/RankingTable";
import "../styles/RankingPageStyle.css";
import { DarkButton } from '../Components/DarkButton';
import { useNavigate } from 'react-router-dom';

function RankingPage() {
    const navigate = useNavigate();
    const handleLogout = () => {
        localStorage.removeItem('token');
        navigate('/');
    };
  return (
        <>
          <Header onLogout={handleLogout} />
            <div className='small-logo-container'>
                <Logo/>
            </div>
            <RankingTable/>
            <div className='center'>
                <DarkButton onClick={()=>{navigate("/main")}}>Powrót</DarkButton>
            </div>
        </>
    );
}

export default RankingPage