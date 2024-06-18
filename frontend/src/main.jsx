import React from 'react'
import ReactDOM from 'react-dom/client'
import LoginPage from './pages/LoginPage.jsx'
import MainPage from './pages/MainPage.jsx'
import RankingPage from './pages/RankingPage.jsx'
import GamePage from './pages/GamePage.jsx'
import ScannerPage from './pages/ScannerPage.jsx'
import { StyledEngineProvider } from "@mui/material";

import './index.css'
import {
    createBrowserRouter,
    RouterProvider,
    Navigate
} from "react-router-dom";
import VisitedBarsPage from './pages/VisitedBarsPage.jsx'
import BarDetailsPage from './pages/BarDetailsPage.jsx'

const PrivateRoute = ({ element }) => {
    const token = localStorage.getItem('token');
    const userId = localStorage.getItem('userId');
    return token && userId ? element : <Navigate to="/" />;
};

const router = createBrowserRouter([
    {
        path: "/",
        element: <LoginPage />,
    },
    {
        path: "main",
        element: <PrivateRoute element={<MainPage />} />,
    },
    {
        path: "ranking",
        element: <PrivateRoute element={<RankingPage />} />,
    },
    {
        path: "alkoholizacja",
        element: <PrivateRoute element={<GamePage />} />,
    },
    {
        path: "odwiedzone-bary",
        element: <PrivateRoute element={<VisitedBarsPage />} />,
    },
    {
        path: "bar-details",
        element: <PrivateRoute element={<BarDetailsPage />} />,
    },
    {
        path: "scanner",
        element: <PrivateRoute element={<ScannerPage />} />,
    }
]);

ReactDOM.createRoot(document.getElementById("root")).render(
    <StyledEngineProvider injectFirst>
        <React.StrictMode>
            <RouterProvider router={router} />
        </React.StrictMode>
    </StyledEngineProvider>
);
