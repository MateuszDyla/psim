import React from 'react'
import '../styles/VisitedBarstyle.css'
import { useNavigate } from 'react-router-dom';

function VisitedBar(props) {

  const navigate = useNavigate();


  return (
    <div id ="visited-bar" onClick={()=>navigate("bary/1")}>
      <p className="bar-name">{props.barName}</p>
      <p className="bar-date">{props.date}</p>
      <img src={props.barImg}/>

    </div>
  )
}

export default VisitedBar