$(document).ready(function() {
  $('.copy-code-button').click(function() {
    let clickedButton = $(this);
    let clickedButtonText = clickedButton.text();
    // let clipboard = new ClipboardJS(clickedButton[0]); // Q: Why doesn't this work? A: TBC
    
    let clickedButtonId = clickedButton.attr('id');    
    let clipboard = new ClipboardJS('#' + clickedButtonId);
    console.log('Created clipboard element');
    clipboard.on('success', function(e) {
      console.log('In clipboard success method');
      e.clearSelection();
      clickedButton.text('Copied!');
      setTimeout(function() {
        clickedButton.text(clickedButtonText);
      }, 2000);
    });
    clipboard.on('error', function(e) {
      e.clearSelection();
      clickedButton.text('Error!');
      setTimeout(function() {
        clickedButton.text(clickedButtonText);
      }, 2000);
    });
  });
});

// var buttons = document.querySelectorAll('.copy-code-button');
// var clipboard = new ClipboardJS(buttons);

// var buttons = document.querySelectorAll('.copy-code-button');
// buttons.forEach(button => {
//   button.on('click', function() {
//     let clickedButton = this;
//   })
// })

// var clipboard = new ClipboardJS('.copy-code-button', {
//   target: function(trigger) {
//     return trigger.parentNode.parentNode;
//   }
// });

// clipboard.on('success', function(e) {
//   // console.log(e);
//   console.log("Successful copy!");  
  
//   // console.log('e.text: ', e.text);
//   // e.text = 'Copied!';  

//   console.log(clipboard.target());
//   // clickedButton = clipboard.clickedButton;
//   // clickedButton.innerText = 'Copied!';  
//   e.clearSelection();
//   // window.getSelection().removeAllRanges();
//   // window.getSelection().addRange(document.createRange());
// });

// clipboard.on('error', function(e) {
//   // console.log(e);
//   console.log("Failed copy!");
// });

// clipboard = Navigation.clipboard;
// function copyCode() {

// }