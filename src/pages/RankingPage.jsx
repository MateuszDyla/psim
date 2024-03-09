import React from 'react'
import Header from '../Components/Header';
import Logo from '../Components/Logo';
import RankingTable from "../Components/RankingTable";
import "../styles/RankingPagestyle.css";
import { Button } from '@mui/material';
import { DarkButton } from '../Components/DarkButton';

function RankingPage() {
  return (
        <>
            <Header/>
            <div className='small-logo-container'>
                <Logo/>
            </div>
            <RankingTable/>
            <div className='center'>
                <DarkButton>Powrót</DarkButton>
            </div>
        </>
    );
}

export default RankingPage