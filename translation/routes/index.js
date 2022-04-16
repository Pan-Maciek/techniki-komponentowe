import { Router } from 'express';
import translate, { languages } from 'translate-google'
var router = Router();

router.get('/languages', (req, res) => res.json(languages))

router.get('/translate', async (req, res) => {
  const { phrase, lang } = req.query
  const langs = lang.substring(1, lang.length - 1).split(',')
  const translations = await Promise.all(langs.map(lang => translate(phrase, { to: lang })))
  res.json([phrase, ...translations])
})

export default router;
