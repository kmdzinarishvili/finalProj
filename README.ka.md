# ფილმების აპლიკაცია
[![en](https://img.shields.io/badge/lang-en-red.svg)](https://github.com/kmdzinarishvili/finalProj/edit/georgianReadme/README.md)
[![pt-br](https://img.shields.io/badge/lang-ka-green.svg)](https://github.com/kmdzinarishvili/finalProj/edit/georgianReadme/README.ka.md)
## დანიშნულება

ამ აპლიკაციის მიზანია მომხმარებლებს მისცეს საშუალება რომ დაათვალიაროს და მოიძიოს ფილმები. 
მომხმარებელს შეუძლია დეტალური ინფორმაცია ნახოს ამ ფილმების შესახებ, როგორიცაა აღწერა და რეიტინგი. 
ასევე, აპლიკაცია მომხმარებელს აძლევს საშუალებას რომ საყვარელი ფილმები მონიშნოს და შეინახოს CSV ფაილში. 

## გამოყენება

1. **ფილმების დათვალიერება:**
   - მომხმარებელს შეუძლია ფილმების სიის დათვალიერება. 

2. **მოძიების ფუნქციონალი:**
   - მომხმარებელის შეუძლია მოიძიოს კონკრეტული ფილმი სათაურის მიხედვით.

3. **ფილმის დეტალები:**
   - ფილმზე დაჭერისას, მომხმარებელს შეუძლია ფილმის დეტალების ნახვა: მაგალითად ფილმის აღწერა და რეიტინგი.  

4. **საყვარელი ფილმები:**
   - გულის ღილაკზე დაჭრვით, მომხმარებელს შეუძლია რომ ფილმი შეინახოს საყვარელ ფილმების სიაში. 
   - საყვარელი ფილმების სქრინზე, მომხმარებელს შეუძლია დაათვალიეროს საყვარელი ფილმების ჩამონათვალი.

5. **შნახვა CSV ფაილში:**
   - მომხმარებელს შეუძლია შეინახოს საყვარელი ფილმების სია CSV ფაილში ოფლაინ წვდომისთვის.

## იმპლემენტაციის დეტალები

1. **ფილმის მონაცემების წამოღება:**
   - აპლიკაცია იყენებს Retrofit-ს მონაცემების წამოსაღებად რემოუთ API-სგან.
   - Moshi გამოყენებულია JSON პარსინგისთვის.
   - Glide გამოყენებულია სურათების ჩატვირთვის და ქეშირებისთვის.

2. **ოფლაინ შენახვა:**
   - საყვარელი ფილმები შენახულია Room-ის მონაცემთა ბაზის მეშვეობით.

3. **ფაილის შენახვა:**
   - Work Manager გამოყენებულია საყვარელი ფილმების CSV ფაილში შესანახად. 

4. **ნავიგაცია:**
   - ნავიგაციის კომპონენტი (Navigation Component) გამოყენებულია splash, detail და container ფრაგმენტებს შორის შორის ნავიგაციისთვის.
   - ViewPager2 გამოყენებულია home და favorite ფრაგმენტებს შორის ნავიგაციისთვის.

5. **Dependency Injection:**
   - Koin გამოყენებულია dependency injection-ისთვის.
   
6. **API ქოლების ლოგირება:**
   - OkHttp3 გამოყენებულია API ქოლების ლოგირებისთვის.
     
7. **Broadcast Receiver:**
   - Broadcast Receiver გამოყენებულია CSV ფაილის ჩამოტვირთვის დასურლების ექშენის დასაჭერად.


## სქრინები

***Splash***

<img src="https://github.com/kmdzinarishvili/finalProj/assets/55915632/50c8b175-44b3-48e3-9c0c-4cabebb20564" alt="splash page screenshot" width="200"/>

***Home***

<img src="https://github.com/kmdzinarishvili/finalProj/assets/55915632/2d157886-c441-4859-9ae2-0fc5d3da4763" alt="home page screenshot" width="200"/>

***Favorites***

<img src="https://github.com/kmdzinarishvili/finalProj/assets/55915632/3ba7d601-b3c7-47c4-9afd-42452bcac476" alt="favorites page screenshot" width="200"/>

***Search***

<img src="https://github.com/kmdzinarishvili/finalProj/assets/55915632/85400d13-3719-4c5c-940b-819f0d8dc401" alt="favorites page screenshot" width="200"/>
