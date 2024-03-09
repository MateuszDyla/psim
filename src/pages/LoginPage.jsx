import { DarkButton } from "../Components/DarkButton.jsx";
import Header from "../Components/Header.jsx"
import Logo from "../Components/Logo.jsx";
import '../styles/LoginPagestyle.css'
import { Button } from "@mui/material";


function LoginPage() {
  return(
    <>
        <Header/>
        <div className ="logo-container">
            <Logo/>
        </div>
        <div id="loginForm">
            <label>
                Login: <input type="text"/>
            </label>
            <br/>
            <label>
                Hasło: <input type="password"/>
            </label>
            <br/>
            <DarkButton>Zaloguj się</DarkButton>
        </div>
    </>
      );
}

export default LoginPage
