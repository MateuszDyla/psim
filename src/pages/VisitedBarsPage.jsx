import React from 'react'
import Header from '../Components/Header'
import Logo from '../Components/Logo'
import { DarkButton } from '../Components/DarkButton'
import { useNavigate } from 'react-router-dom';

function VisitedBarsPage() {
    const navigate = useNavigate();


  return (
        <>
            <Header/>
            <div className="small-logo-container">
                <Logo/>
            </div>

            <div className='center'>
                <DarkButton onClick={()=>{navigate("/main")}}>Powrót</DarkButton>
            </div>
        </>
    )
}

export default VisitedBarsPage