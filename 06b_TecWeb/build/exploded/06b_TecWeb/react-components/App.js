'use strict';

class App extends React.Component {
  constructor(){
        super();
        this.state = {
        result: "", result2:""
        }
        this.onClick = this.onClick.bind(this); 
  }

  onClick(e) {
        let button = e.target.name
        if(button === "="){

            this.calculate()
        }

      else if(button === "C"){
          this.reset()
      }
      else if(button === "CE"){
          this.backspace()
      }

      else {
          this.setState({
        result: this.state.result + button
          })
      }
  };


  calculate() {
        try {
        	if(this.state.result.startsWith("log") || this.state.result.startsWith("sqrt") || this.state.result.startsWith("e") || this.state.result.startsWith("1/")){
        		requestRandomIntGeneration(
        				"./Calculatorv2?operazione="+this.state.result,
						(resultCalc) => {
							this.setState({result2: resultCalc})
						}
					);
        	}else{
            this.setState({
              result2: (eval(this.state.result) || "" ) + ""
            })}
        } catch (e) {
            this.setState({
          result2: "error"
            })

        }
  };

  reset(){
      this.setState({
          result: "",
          result2:""
      })
        };


  backspace(){
      this.setState({
          result: this.state.result.slice(0, -1)
      })
  };

  render() {
      return (

        <div className="calculator-body">
            <h1>Calcolatrice</h1>
            <Display result={this.state.result}/>
            <Display result={this.state.result2}/>
            <KeyBoard onClick={this.onClick}/>
        </div>

      );
  }
}
