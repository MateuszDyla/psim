//Author: Mateusz Dyla
import React from 'react'
import '../styles/VisitedBarStyle.css'
import { useNavigate } from 'react-router-dom';


//"Tile" with the name and image of the bar, and the last visit date.
function VisitedBarRectangle(props) {

  const navigate = useNavigate();


  return (
    <div id ="visited-bar" onClick={()=>navigate("/bary?id=1")}>
      <p className="bar-name">{props.barName}</p>
      <p className="bar-date">{props.date}</p>
      <img src={props.barImg}/>

    </div>
  )
}

export default VisitedBarRectangle