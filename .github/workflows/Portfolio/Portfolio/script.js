document.addEventListener("DOMContentLoaded", () => {
    
    // 1. Dynamic Footer Year
    // This automatically updates the year in your footer so you never have to change it manually.
    const yearSpan = document.getElementById("current-year");
    if (yearSpan) {
        yearSpan.textContent = new Date().getFullYear();
    }

    // 2. Interactive Profile Picture
    // Adds a simple pop-up greeting when a visitor clicks your photo.
    const profileImg = document.querySelector(".profile-img");
    if (profileImg) {
        profileImg.addEventListener("click", () => {
            alert("Hello! Thanks for visiting my Android Development portfolio.");
        });
        
        // Change the cursor to a pointer so users know it's clickable
        profileImg.style.cursor = "pointer";
    }
});