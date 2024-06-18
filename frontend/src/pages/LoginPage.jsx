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
                const token = await response.text();
                localStorage.setItem('token', token);

                const userIdResponse = await fetch('http://localhost:8080/user/getUserId', {
                    method: 'GET',
                    headers: {
                        'Authorization': `Bearer ${token}`
                    }
                });

                if (userIdResponse.ok) {
                    const userId = await userIdResponse.json();
                    localStorage.setItem('userId', userId);
                    navigate('/main');
                } else {
                    setError('Failed to fetch user ID');
                }
            } else {
                const errorData = await response.text();
                setError(errorData || 'Login failed');
            }
        } catch (error) {
            setError(`Network error: failed to connect to the server (${error.message})`);
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