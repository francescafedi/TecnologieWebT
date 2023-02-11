'use strict';

class App extends React.Component {
  constructor(){
        super();
        this.state = {
          formState: {
            identificativo: 0,
            nomeprodotto: '',
            quantita: 0,
            prezzoimponibile: 0,
            iva:1,
            prezzofinale:0,
            tipo:'acquisto',
          },
          formStateVendita: {
            identificativo: 0,
            nomeprodotto: '',
            quantita: 0,
            prezzoimponibile: 0,
            iva:1,
            prezzofinale:0,
            tipo:'vendita',
          },
          lavList: [],
          totaleVendita: '',
          totaleAcquisto:'' 
        }
      
  }


  handleChangeVendita = (e) => {

		const target = e.target
		const value = target.type === 'checkbox' ? target.checked : target.value
		const name = target.name
    let prezzofinalecalcolato=0;
    if(name=='prezzoimponibile'){
      prezzofinalecalcolato=this.state.formStateVendita.iva*value
    }
    if(name=='iva'){
      prezzofinalecalcolato=this.state.formStateVendita.prezzoimponibile*value
    }
    this.setState(prev => ({
			formStateVendita: {
				...prev.formStateVendita,
				[name]: value,
        prezzofinale: prezzofinalecalcolato,
			}
		}))

  
	}

  handleChangeAcquisto = (e) => {

		const target = e.target
		const value = target.type === 'checkbox' ? target.checked : target.value
		const name = target.name
    let prezzofinalecalcolato=0;
    if(name=='prezzoimponibile'){
      prezzofinalecalcolato=this.state.formState.iva*value
    }
    if(name=='iva'){
      prezzofinalecalcolato=this.state.formState.prezzoimponibile*value
    }
    this.setState(prev => ({
			formState: {
				...prev.formState,
				[name]: value,
        prezzofinale: prezzofinalecalcolato,
			}
		}))
  
	}

	handleClickVendita = () => {

		this.setState(prev => ({
			lavList: [...prev.lavList, prev.formStateVendita] 
		}))
	}

  handleClickAcquisto = () => {

		this.setState(prev => ({
			lavList: [...prev.lavList, prev.formState] 
		}))
	}

  handleReset = () => {
    this.setState( () =>({
      formState: {
        identificativo: '',
        nomeprodotto: '',
        quantita: ' ',
        prezzoimponibile: '',
        iva:'',
        prezzofinale:'',
        tipo:'acquisto',
      },
      formStateVendita: {
        identificativo: '',
        nomeprodotto: '',
        quantita: '',
        prezzoimponibile: '',
        iva:'',
        prezzofinale:'',
        tipo:'vendita',
      },
      lavList: [],
      totaleVendita: '',
      totaleAcquisto:'' 
    }))
  }

  handleSum = () => {
    let totaleVendita=0;
    let totaleAcquisto=0;
    for(var i=0;i<this.state.lavList.length; i++){
      if(this.state.lavList[i].tipo=="acquisto"){
        totaleAcquisto+=this.state.lavList[i].prezzofinale
      }
      if(this.state.lavList[i].tipo=="vendita"){
        totaleVendita+=this.state.lavList[i].prezzofinale
      }
    }
    this.setState( () =>({
      totaleVendita: totaleVendita,
      totaleAcquisto: totaleAcquisto
    }))
  }



  render() {
    console.log("Qui parla app")
    console.log(this.state.lavList);
      return (

        <div className="calculator-body">
            <SezioneCompilazione 
                title={'Compilazione fatture di acquisto'}
                onChange={this.handleChangeAcquisto}
                onClick={this.handleClickAcquisto}
                ivaarray={[10, 11,12,13,14,15,16,17,18,19,20,21,22]}
                tipo={'acquisto'}
                {... this.state.formState}/>
                 <SezioneCompilazione 
                title={'Compilazione fatture di vendita'}
                onChange={this.handleChangeVendita}
                onClick={this.handleClickVendita}
                ivaarray={[4,5,6]}
                tipo={'vendita'}
                {... this.state.formStateVendita}/>
          <Sequenza list={this.state.lavList} reset={this.handleReset} sum={this.handleSum} totaleAcquisto={this.state.totaleAcquisto} totaleVendita={this.state.totaleVendita}></Sequenza> 
            
        </div>

      );
  }
}
