'use strict';


function Sequenza (props) {
    let elements = props.list.map(a => 

           a.fuorilista==false ? `Dado 1: ${a.numeroEstratto1} - Dado 2: ${a.numeroEstratto2} - Dado 3:${a.numeroEstratto3}` : null
        
        );
        console.log(elements)
        return (
            
        <div>
            <h3>SEQUENZA LANCI</h3>
            <textarea rows="10" cols="50" value={elements.join("\n")}></textarea>
            <br></br>
        </div>
        )
  }
