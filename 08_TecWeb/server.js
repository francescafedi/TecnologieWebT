//Es.1.5
const http = require('http');
const fs = require('fs');
var  readline= require('readline');

const hostname = '127.0.0.1';
const port = 3000;

const server = http.createServer((req, res) => {
  res.statusCode = 200;
  res.setHeader('Content-Type', 'text/plain');
  var rl= readline.createInterface({
      input: fs.createReadStream("myFile.txt"),
      output: process.stdout,
      terminal:false
  });
  var countline=0;
  var countparole=0;
  var countparoleperline =[];
  rl.on('line', (input) =>{
    
    countparole+=contaParole(input);
    countparoleperline[countline]=contaParole(input);
    countline++;
  });


  rl.on('close', () =>{
        res.write("Totale righe: "+countline +", Totale parole: "+countparole +"\n");
        var max=0;
        for(var i =0; i<countline-1;i++){
          res.write("Parole per riga "+ i +": "+countparoleperline[i]+"\n");
          if(countparoleperline[max]<=countparoleperline[i]){
            max=i;
          }
        }
        res.write("La riga con il massimo numero di parole e' "+ max);
        res.end();
      
  });
  

 function readDoneCallback(error, dataBuffer) {
  // convenzione Node per callback: primo argomento è oggetto
  // js di errore
  if (!error) {
 // console.log("smallFile contents", dataBuffer.toString());
  
  res.write('<html><body><h1>Numero parole:'+ contaParole(dataBuffer.toString())+'</h1></body></html>');
  res.end();
  }
  }


  function contaParole(testo){
    var res=testo.trim().split(/[\s]+/);
    return res.length;
  }
});

server.listen(port, hostname, () => {
  console.log(`Server running at http://${hostname}:${port}/`);
});


/* Esercizio 1
const http = require('http');
const fs = require('fs');

const hostname = '127.0.0.1';
const port = 3000;

const server = http.createServer((req, res) => {
  res.statusCode = 200;
  res.setHeader('Content-Type', 'text/html');
  fs.readFile("myFile.txt", readDoneCallback);


 function readDoneCallback(error, dataBuffer) {
  // convenzione Node per callback: primo argomento è oggetto
  // js di errore
  if (!error) {
  console.log("smallFile contents", dataBuffer.toString());
  
  res.write('<html><body><h1>Numero parole:'+ contaParole(dataBuffer.toString())+'</h1></body></html>');
  res.end();
  }
  }


  function contaParole(testo){
    var res=testo.trim().split(/[\s]+/);
    return res.length + " parole: " + res;
  }
});

server.listen(port, hostname, () => {
  console.log(`Server running at http://${hostname}:${port}/`);
});



*/
