//Author: Mateusz Dyla

import { useNavigate } from "react-router-dom";
import { DarkButton } from "../Components/DarkButton";
import Header from "../Components/Header";
import Logo from "../Components/Logo";
import "../styles/MainPagestyle.css"
import { Button } from "@mui/material";
import { StyledEngineProvider } from "@mui/material";



function MainPage(){
    const navigate = useNavigate();

    return(
        <>
            <Header/>
            <div className="logo-container">
                <Logo/>
            </div>
            <br />
            <div id="main-page-buttons" >
                <DarkButton onClick={()=>{navigate("/alkoholizacja")}} id="start-adv-button">Zacznij przygodę!</DarkButton>
                <DarkButton onClick={()=>{navigate("/odwiedzone-bary")}}>Odwiedzone bary</DarkButton>
                <DarkButton onClick={()=>{navigate("/ranking")}}>Ranking maratonu</DarkButton>
            </div>
        </>
    );
}
export default MainPage