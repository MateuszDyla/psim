//Author: Mateusz Dyla, Marta Boryczka


import { Component } from "react";
import "../styles/HeaderStyle.css";
import { DarkButton } from "./DarkButton";

class Header extends Component {
    render() {
        const { onLogout } = this.props;
        return (
            <>
                <div id="top-bar">
                    <DarkButton id="logout-button" onClick={onLogout}>Wyloguj się</DarkButton>
                </div>
            </>
        );
    }
}

export default Header