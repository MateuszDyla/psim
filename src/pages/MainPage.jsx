import { DarkButton } from "../Components/DarkButton";
import Header from "../Components/Header";
import Logo from "../Components/Logo";
import "../styles/MainPagestyle.css"
import { Button } from "@mui/material";
import { StyledEngineProvider } from "@mui/material";



function MainPage(){
    return(
        <>
            <Header/>
            <div className="logo-container">
                <Logo/>
            </div>
            <br />
            <div id="main-page-buttons" >
                <DarkButton id="start-adv-button">Zacznij przygodę!</DarkButton>
                <DarkButton>Odwiedzone bary</DarkButton>
                <DarkButton>Ranking maratonu</DarkButton>
            </div>
        </>
    );
}
export default MainPage