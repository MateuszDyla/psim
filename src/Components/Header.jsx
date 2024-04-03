//Author: Mateusz Dyla

import { Component } from "react";
import "../styles/Headerstyle.css";
import { Button } from "@mui/material";
import { DarkButton } from "./DarkButton";

//Logout bar
class Header extends Component {
    render() {
        return(
            <>
                <div id="top-bar">
                    <DarkButton id="logout-button">Wyloguj się</DarkButton>
                </div>
            </>
        );
    }
}

export default Header