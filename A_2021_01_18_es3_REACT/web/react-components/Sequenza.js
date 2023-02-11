'use strict';


function Sequenza (props) {

    //identificativo (numero intero), 
//nome prodotto, 
//quantità, 
//prezzo imponibile, 
//IVA (percentuale),  un elemento che permetterà la selezione fra i valori 10 e 22.
//prezzo finale, campo prezzo finale sarà a sola lettura e dovrà essere valorizzato automaticamente quando l’utente avrà inserito entrambi i campi prezzo imponibile e IVA.
//tipo -  Il campo tipo potrà assumere uno fra i due valori “acquisto” o “vendita”.
console.log("LISTA DA SEQUENZA")
console.log(props.list)
    let elements = props.list.map(a => 
        `<${a.identificativo}> <${a.nomeprodotto}> <${a.quantita}> <${a.prezzoimponibile}><${a.iva}><${a.prezzofinale}> <${a.tipo}>`        
        );
        return (
            
        <div>
            <h3>LISTA</h3>
            <textarea rows="10" cols="50" value={elements.join("\n")}></textarea>
            <br></br>
            <legend>Tot.acquisto</legend>
            <input type="text" name="totaleacquisto" disabled value={props.totaleAcquisto}></input>
            <legend>Tot.Vednita</legend>
            <input type="text" name="totalevendita" disabled value={props.totaleVendita}></input>
            <button className="card__button" onClick={props.sum} >Totali</button>
            <button className="card__button" onClick={props.reset} >Reset</button>
        </div>
        )
  }
