var tab= document.getElementById("tab");
var but=document.getElementById("k");
 console.log("trjjjiggred")
var inpop=document.getElementById("inpop");
var inCapitale=document.getElementById("inCapital");
var inName=document.getElementById("inName");

console.log(but.innerText);
function add(nm,cap,pop){
    var row =tab.insertRow(tab.rows.length);
    cell0=row.insertCell(0);
    cell1=row.insertCell(1);
    cell2=row.insertCell(2);
    cell0.innerText=nm;
    cell1.innerText=cap;
    cell2.innerText=pop;
    
}    
document.getElementById("k").onclick = function(){
    console.log("triggred")
    if(inpop.value!=""&&inCapital!=""&&inName!=""){
        add(inName.value,inCapital.value,inpop.value);
        let p={
			name:inName.value,
			capitale:inCapital.value,
			population:inpop.value
		}
         	fetch("http://localhost:8080/addpays",{
		method:"POST",
		headers:{
			'Content-Type': 'application/json',
		},
		body: JSON.stringify(p),
	})
		.then(respense=>respense.json())
		.then((p) => {
    	console.log('Success:', p);
  	})
		.catch((error) => {
    	console.error('Error:', error);
  });	
        
        
    }
    inName.value="";
    inCapitale.value="";
    inpop.value="";


}