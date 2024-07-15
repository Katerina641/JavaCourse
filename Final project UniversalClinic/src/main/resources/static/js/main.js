
function closeNav() {
    const nav = document.querySelector('.left-nav-js')
    const wrap = document.querySelector('.wrapper')

    if(nav.classList.contains('closed')){
        document.querySelector('.left-nav-js').classList.remove('closed')
        document.querySelector('.wrapper').classList.remove('nav-closed')
        return
    }

    document.querySelector('.left-nav-js').classList.add('closed')
    document.querySelector('.wrapper').classList.add('nav-closed')
}

document.querySelector('.nav-btn-js').addEventListener('click', closeNav)


// Function to check the width
function checkWidth() {
    if (window.innerWidth < 940) {
        closeNav()
    }
}

// Add event listener for DOMContentLoaded
document.addEventListener('DOMContentLoaded', function() {
    checkWidth();
});

document.querySelectorAll('.search-by-js').forEach(e => {
    e.addEventListener('input', function() {
        const lastName = this.value;
        console.log(lastName);
        const type = this.dataset.type
        const wrap = this.closest('.search-wrap')
        fetchDoctorsByLastName(lastName, wrap, type);
    });
})

function fetchDoctorsByLastName(lastName, wrap, type) {
    const url = `/${type}/by-lastname/${lastName}`;

    fetch(url)
        .then(response => {
            if (response.status === 204) {
               console.log('No doctors found with the last name provided.')
                wrap.querySelector('.search-results-js').innerHTML = ``
                wrap.querySelector('.search-results-js').classList.remove('active')
               return null;
            }
            if (!response.ok) {
                wrap.querySelector('.search-results-js').innerHTML = ``
                wrap.querySelector('.search-results-js').classList.remove('active')
                throw new Error('Network response was not ok');
            }
            return response.json();
        })
        .then(data => {
            console.log(JSON.stringify(data))

            if(data !== null){
                const results = data.map(el => {
                    return `<li data-id="${el.id}">
                        <div class="lastName">${el.lastName}</div>
                        <div>${el.firstName}</div>
                        <div><a href="/${type}/${el.id}/update">Open</a></div>
                        </li>`
                }).join('')

                wrap.querySelector('.search-results-js').innerHTML = `<ol>${results}</ol>`
                wrap.querySelector('.search-results-js').classList.add('active')
            } else {
                wrap.querySelector('.search-results-js').innerHTML = ``
                wrap.querySelector('.search-results-js').classList.remove('active')
            }

        })
        .catch(error => {
            console.error('There was a problem with the fetch operation:', error);
        })
        .finally(() => {
            try{
                const wrapResults = wrap.querySelector('.search-results-js')
                wrapResults.querySelectorAll('li').forEach(li => {
                    li.addEventListener('click', () => {
                        console.log('li click')
                        const id = li.dataset.id
                        const lastName = li.querySelector('.lastName').innerText
                        li.closest('.search-wrap').querySelector('.id-value').value = id;
                        wrap.closest('.search-wrap').querySelector('.search-by-js').value = lastName
                        wrapResults.classList.remove('active')
                        wrapResults.innerHTML = ''
                    })
                })
            } catch (e) {
                console.log(e)
            }
        });
}
