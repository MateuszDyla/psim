import React from 'react'
import "../styles/PiwoBar.css"

function PiwoBar(props){
  return (
        <>
        <div id="piwo">
          <img id="piwo-bar-img" src = {props.img}/>
          <br />
          <p><b>{props.barName}</b></p>
          <span>{props.barDesc}</span>
        </div>
        </>
    )
}

export default PiwoBar