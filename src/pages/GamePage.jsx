const width = window.innerWidth

import React from 'react'
import Header from '../Components/Header'
import Logo from '../Components/Logo'
import PiwoBar from '../Components/PiwoBar';
import { DarkButton } from '../Components/DarkButton';
import Timer from '../Components/Timer';
import '../styles/GamePagestyle.css';



function GamePage() {
    if (width > 500) {
        return (
            <div> z kompem raczej nie bedziesz biegal alkoholiku</div>
        )
    }
    return (
        <div className="background">
            <Header />
            <div className='small-logo-container'>
                <Logo/>
            </div>
            <PiwoBar img="https://travel-mates.pl/wp-content/uploads/2022/11/Cybermachina0-1080x1080.jpg" barName="Cybermachina" barDesc="Wrocławska 15"/>

            <div className="timer">
                <Timer duration={30*60*1000} />
            </div>
            <div class="bottom">
                <DarkButton>Powrót</DarkButton>
            </div>
        </div>
    )
}
export default GamePage
