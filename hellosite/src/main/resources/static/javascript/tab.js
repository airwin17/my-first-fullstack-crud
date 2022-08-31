var tab= document.getElementById("tab");
var but=document.getElementById("submit");
var inpop=document.getElementById("inpop");
var inCapitale=document.getElementById("inCapital");
var inName=document.getElementById("inName");
var selected;
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
document.getElementById("submit").onclick = function(){
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
function handler(x){
    if(x==selected){
        console.log("45");
        x.style.backgroundColor="white";
        document.querySelector("#update").disabled= true;
        document.querySelector("#remove").disabled= true;
        selected=null;
    }else{
        console.log("75");
        if(selected!=null){
            selected.style.backgroundColor= "white";
        }
        x.style.backgroundColor="blue";
        document.querySelector("#update").disabled= false;
        document.querySelector("#remove").disabled= false;
        selected=x;
    }
}
document.querySelector("#update").onclick=function(){
    
    if(inpop.value!=""&&inCapital!=""&&inName!=""){
        selected.cells[0].innerText=inName.value;
        selected.cells[1].innerText=inCapital.value;
        selected.cells[2].innerText=inpop.value;
        document.querySelector("#update").disabled=true;
        document.querySelector("#remove").disabled=true;
        selected.backgroundColor="white";
        selected=null;
    }
}
document.querySelector("#remove").onclick=function(){
    document.querySelector("#tab").deleteRow(selected.rowIndex);
    
    document.querySelector("#remove").disabled=true;
    document.querySelector("#update").disabled=true;
    let m={
        name:selected.cells[0].innerText,
        capitale:selected.cells[1].innerText,
        population:selected.cells[2].innerText
    }
    fetch("http://localhost:8080/deletepays",{
		method:"DELETE",
		headers:{
			'Content-Type': 'application/json',
		},
		body: JSON.stringify(m),
		})
        .then(res => {
            if (res.ok) { console.log("HTTP request successful") }
            else { console.log("HTTP request unsuccessful") }
            return res.json
        })
        .then(response=> console.log(response.str));
        selected=null;
}