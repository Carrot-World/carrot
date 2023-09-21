/* userSellPage.js */

$(window).scroll(function(){
    $('.header').css('left', 0-$(this).scrollLeft());
  });
  
  
  // 쓰로틀
  // 물품목록 4개씩 고정하기 위해
  function rescaleCard() {
    const wrapperWidth = document.querySelector("div.items").offsetWidth;
    const offset = 30;
    const cardWidth = Math.ceil(wrapperWidth * 270 / 1336);

    const cards = document.querySelectorAll("div.card");
    const cardImg = document.querySelectorAll("img.card-img-top");
    console.log(cardWidth);
    for (let i=0; i<cards.length; i++) {
      cards[i].style.width = `${cardWidth}px`;
      cardImg[i].style.height = `${cardWidth}px`;
    }
  }

  rescaleCard();
  
  function throttle(fn, delay) {
    let waiting = false;
    return () => {
      if (!waiting) {
        fn.apply(this);
        waiting = true;
        setTimeout(() => {
          waiting = false;
        }, delay);
      }
    }
  }

  window.addEventListener("resize", throttle(rescaleCard, 100));