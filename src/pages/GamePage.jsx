const width = window.innerWidth

import React from 'react'
import Header from '../Components/Header'
import Logo from '../Components/Logo'
import PiwoBar from '../Components/PiwoBar';
import { DarkButton } from '../Components/DarkButton';


function GamePage() {
    if (width > 500) {
        return (
            <div> z kompem raczej nie bedziesz biegal alkoholiku</div>
        )
    }
    return (
        <>
            <Header></Header>
            <div className='small-logo-container'>
                <Logo/>
            </div>
            <PiwoBar img="https://travel-mates.pl/wp-content/uploads/2022/11/Cybermachina0-1080x1080.jpg" barName="Cybermachina" barDesc="Wrocławska 15"/>
            <div class="center">
                <DarkButton>Powrót</DarkButton>
            </div>
        </>
    )
}

export default GamePage