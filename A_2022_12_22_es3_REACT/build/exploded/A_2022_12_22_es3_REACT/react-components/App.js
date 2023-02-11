'use strict';

class App extends React.Component {
  constructor(){
        super();
        this.state = {
          formState: {
            numeroEstratto1: 0,
            numeroEstratto2: 0,
            numeroEstratto3: 0,
            fuorilista: true
          },
          lanci: []
        }
      
  }

  lanciaDado () {
    const a= Math.round(Math.random()*5)+1
    const b= Math.round(Math.random()*5)+1
    const c= Math.round(Math.random()*5)+1
    this.setState(prev => ({
			formState:{
        numeroEstratto1:a,
        numeroEstratto2:b,
        numeroEstratto3:c,
        fuorilista: ((a+b+c)>6 && (a+b+c)<15) ? false : true,
      },
			lanci: [...prev.lanci, prev.formState] 
		}))
    
}


  render() {
    console.log(this.state.lanci);
      return (

        <div className="calculator-body">
            <Dado lanciaDado={() => this.lanciaDado()} {... this.state.formState}/>
            <Sequenza list={this.state.lanci}/>
            
        </div>

      );
  }
}
