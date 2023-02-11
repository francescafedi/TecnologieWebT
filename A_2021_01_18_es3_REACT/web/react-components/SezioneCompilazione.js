'use strict';


function SezioneCompilazione (props) {
//identificativo (numero intero), 
//nome prodotto, 
//quantità, 
//prezzo imponibile, 
//IVA (percentuale),  un elemento che permetterà la selezione fra i valori 10 e 22.
//prezzo finale, campo prezzo finale sarà a sola lettura e dovrà essere valorizzato automaticamente quando l’utente avrà inserito entrambi i campi prezzo imponibile e IVA.
//tipo -  Il campo tipo potrà assumere uno fra i due valori “acquisto” o “vendita”.

//bottonefinale


//Props
/*
title
onChnge
handleClick
iva
tipo
prezzofinale

co
*/

        return (
        <div>
            <h3>{props.title}</h3>
            <legend>Identificativo</legend>
            <input onChange={props.onChange} type="text" name="identificativo" value={props.identificativo}></input>
            <legend>Nome prodotto</legend>
            <input onChange={props.onChange} type="text" name="nomeprodotto" value={props.nomeprodotto}></input>
            <legend>Quantità</legend>
            <input onChange={props.onChange} type="number" name="quantita" value={props.quantita}></input>
            <legend>Prezzo imbonibile</legend>
            <input onChange={props.onChange} type="text" name="prezzoimponibile"  value={props.prezzoimponibile}></input>
            <legend>Iva</legend>
            <select name="iva" id="iva" onChange={props.onChange}>
            {
                props.ivaarray.map( (x) => 
                <option value={x}>{x}</option> )
            }
            </select>
            <legend>Prezzo finale</legend>
            <input type="text" name="prezzofinale" disabled value={props.prezzofinale}></input>
            <legend>Tipo</legend>
            <input type="text" name="tipo" disabled value={props.tipo}></input>
            <button className="card__button" onClick={props.onClick} >Inserisci fattura</button>
            <br></br>
        </div>
        )
  }
