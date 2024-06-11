//Author: Mateusz Dyla

import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { DarkButton } from "../Components/DarkButton.jsx";
import Logo from "../Components/Logo.jsx";
import '../styles/LoginPageStyle.css';


function LoginPage() {
    const [login, setLogin] = useState('');
    const [password, setPassword] = useState('');
    const [error, setError] = useState('');
    const navigate = useNavigate();

    const handleLogin = async () => {
        try {
            const response = await fetch('http://localhost:8080/user/login', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify({ username: login, password }),
            });

            if (response.ok) {
                const data = await response.text();
                localStorage.setItem('token', data);
                navigate('/main');
            } else {
                const errorData = await response.text();
                setError(errorData || 'Nieudane logowanie');
            }
        } catch (error) {
            setError(`Błąd sieci: nie udało się połączyć z serwerem (${error.message})`);
        }
    };

    return (
        <>
            <div className="logo-container">
                <Logo />
            </div>
            <div id="loginForm">
                <label>
                    Login: <input type="text" value={login} onChange={(e) => setLogin(e.target.value)} />
                </label>
                <br />
                <label>
                    Hasło: <input type="password" value={password} onChange={(e) => setPassword(e.target.value)} />
                </label>
                <br />
                {error && <div className="error">{error}</div>}
                <DarkButton onClick={handleLogin}>Zaloguj się</DarkButton>
            </div>
        </>
    );
}

export default LoginPage;
