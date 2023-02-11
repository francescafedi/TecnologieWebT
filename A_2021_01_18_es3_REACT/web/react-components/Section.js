'use strict';

class Display extends React.Component {

    render() {
    let result = this.props.result;
    return (
        <div className="section">
            <h2>SEQUENZA LANCI</h2>
            <div></div>
        <p>{result}</p>
        </div>
        );
    }
}
