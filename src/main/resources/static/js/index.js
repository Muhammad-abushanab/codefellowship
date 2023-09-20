 let btn = document.querySelector("#editProfileButton");
let form = document.getElementById("#profileForm");
 const inputFields = document.querySelectorAll('input[disabled]');
 btn.addEventListener("click",function(){
 if(btn.innerText.trim() === 'Save Changes'){
      btn.setAttribute("type","submit");
  }
  document.querySelector("#bio").removeAttribute("disabled");
  inputFields.forEach(function(input){
    input.removeAttribute("disabled");
  })
  btn.innerHTML= "Save Changes";

 })