import React from 'react'
import Header from '../Components/Header'
import Logo from '../Components/Logo'
import { DarkButton } from '../Components/DarkButton'
import { useNavigate } from 'react-router-dom';
import VisitedBar from '../Components/VisitedBar';
import "../styles/VisitedBarsPage.css"

function VisitedBarsPage() {
    const navigate = useNavigate();


  return (
        <>
            <Header/>
            <div className="small-logo-container">
                <Logo/>
            </div>

            <div id ="visited-bars-placeholder">
                <VisitedBar barImg= {"../src/assets/cybermachina.png" } barName="cybermachina" date="24.02.2022"/>
                <VisitedBar/>
                <VisitedBar/>
            </div>

            <div className='center'>
                <DarkButton onClick={()=>{navigate("/main")}}>Powrót</DarkButton>
            </div>
        </>
    )
}

export default VisitedBarsPage