let updateFiles = document.getElementsByClassName('updatefile');
		let updateImgs = document.getElementsByClassName('updateimg');
		for(let i = 0; i < updateFiles.length; i++){
		  updateFiles[i].addEventListener('change', function(e){
		    updateImgs[i].src = URL.createObjectURL(e.target.files[0]);
		  })
		} 