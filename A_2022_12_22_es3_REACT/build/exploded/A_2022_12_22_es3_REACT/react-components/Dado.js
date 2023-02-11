'use strict';


function Dado (props) {

        return (
        <div>
            <h3>LANCIO DADI</h3>
            <span>{props.numeroEstratto1}</span> -
            <span> {props.numeroEstratto2}</span> - 
            <span> {props.numeroEstratto3} </span>
            <button className="card__button" onClick={props.lanciaDado} >Lancia il Dado</button>
            <br></br>
        </div>
        )
  }
