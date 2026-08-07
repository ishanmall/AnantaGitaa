/**
 * Ananta Gita - Interactive Landing Page Script
 * Developer: Ishan Mall
 */

document.addEventListener("DOMContentLoaded", () => {
  // 1. Animated Numbers Counter for Key Features
  const animateCounters = () => {
    const counters = document.querySelectorAll(".counter");
    counters.forEach((counter) => {
      const target = +counter.getAttribute("data-target");
      let count = 0;
      const speed = target / 50; // Adjust counting duration

      const updateCount = () => {
        count += speed;
        if (count < target) {
          counter.innerText = Math.ceil(count);
          setTimeout(updateCount, 25);
        } else {
          counter.innerText = target;
        }
      };

      updateCount();
    });
  };

  // Trigger counter animation
  animateCounters();

  // 2. Play Store Button Ripple Effect
  const downloadBtn = document.querySelector(".download-btn");
  if (downloadBtn) {
    downloadBtn.addEventListener("click", function (e) {
      const circle = document.createElement("span");
      const diameter = Math.max(this.clientWidth, this.clientHeight);
      const radius = diameter / 2;

      const rect = this.getBoundingClientRect();
      circle.style.width = circle.style.height = `${diameter}px`;
      circle.style.left = `${e.clientX - rect.left - radius}px`;
      circle.style.top = `${e.clientY - rect.top - radius}px`;
      circle.classList.add("ripple");

      const existingRipple = this.querySelector(".ripple");
      if (existingRipple) {
        existingRipple.remove();
      }

      this.appendChild(circle);
    });
  }

  // 3. Simple Dynamic Background Subtle Sparkles
  const createSparkles = () => {
    const container = document.body;
    for (let i = 0; i < 15; i++) {
      const sparkle = document.createElement("div");
      sparkle.className = "sparkle";
      sparkle.style.left = `${Math.random() * 100}vw`;
      sparkle.style.top = `${Math.random() * 100}vh`;
      sparkle.style.animationDuration = `${3 + Math.random() * 5}s`;
      sparkle.style.animationDelay = `${Math.random() * 2}s`;
      container.appendChild(sparkle);
    }
  };

  createSparkles();
});