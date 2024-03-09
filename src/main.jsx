import React from 'react'
import ReactDOM from 'react-dom/client'
import LoginPage from './pages/LoginPage.jsx'
import MainPage from './pages/MainPage.jsx'
import RankingPage from './pages/RankingPage.jsx'
import GamePage from './pages/GamePage.jsx'
import { StyledEngineProvider } from "@mui/material";

import './index.css'
import {
  createBrowserRouter,
  RouterProvider,
} from "react-router-dom";
import VisitedBarsPage from './pages/VisitedBarsPage.jsx'


const router = createBrowserRouter([
  {
    path: "/",
    element: <LoginPage/>,
  },
  {
    path: "main",
    element: <MainPage/>,
  },
  {
    path: "ranking",
    element: <RankingPage/>,
  },
  {
    path: "alkoholizacja",
    element: <GamePage/>,
  },
  {
    path: "odwiedzone-bary",
    element: <VisitedBarsPage/>
  }

]);

ReactDOM.createRoot(document.getElementById("root")).render(
  <StyledEngineProvider injectFirst>
  <React.StrictMode>
    <RouterProvider router={router} />
  </React.StrictMode>
  </StyledEngineProvider>
);