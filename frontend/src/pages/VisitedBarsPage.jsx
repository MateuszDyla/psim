//Author: Mateusz Dyla

import React from 'react'
import Header from '../Components/Header'
import Logo from '../Components/Logo'
import { DarkButton } from '../Components/DarkButton'
import { useNavigate } from 'react-router-dom';
import VisitedBarRectangle from '../Components/VisitedBarRectangle';
import "../styles/VisitedBarsPageStyle.css"

function VisitedBarsPage() {
    const navigate = useNavigate();


  return (
        <>
            <Header/>
            <div className="small-logo-container">
                <Logo/>
            </div>

            <div id ="visited-bars-placeholder">
                <VisitedBarRectangle barImg= {"../src/assets/cybermachina.png" } barName="cybermachina" date="24.02.2022"/>
                <VisitedBarRectangle barImg= {"../src/assets/remont.png" } barName="remont" date="24.02.2022"/>
                <VisitedBarRectangle barImg= {"../src/assets/piatak.png" } barName="piątak" date="24.02.2022"/>
            </div>

            <div className='center'>
                <DarkButton onClick={()=>{navigate("/main")}}>Powrót</DarkButton>
            </div>
        </>
    )
}

export default VisitedBarsPage