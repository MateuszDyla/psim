//Author: Mateusz Dyla

import React from 'react'
import logo from "../assets/logo.png";
import "../styles/Logostyle.css"

//Logo visible at the top of the page
function Logo() {
  return (
        <img src={logo} id="logo" />
    )
}

export default Logo