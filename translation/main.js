import translate, { languages } from 'translate-google'


const tranObj = [
  "How are you?",
  "this library is free to use",
  "this is an example",
  "word",
  "world"
]

!async function () {
  const translation = await Promise.all([
    Promise.resolve(tranObj), 
    translate(tranObj, { to: 'pl' }), 
    translate(tranObj, { to: 'uk' }),
    translate(tranObj, { to: 'sk' }),
    translate(tranObj, { to: 'el' }),
    translate(tranObj, { to: 'ja' }),
    translate(tranObj, { to: 'zu' })
  ])

  console.table(languages)
  console.table(translation)
}()