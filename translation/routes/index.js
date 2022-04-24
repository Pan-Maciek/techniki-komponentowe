import { Router } from 'express';
import translate, { languages } from 'translate-google'
var router = Router();

router.get('/languages', (req, res) => res.json(languages))

router.get('/translate', async (req, res) => {
  const phrase = decodeURIComponent(req.query.phrase)
  const langs = req.query.lang?.replace(/\s+|[\[\]]/g, '')?.split(',')?.filter(Boolean) ?? []
  const translations = await Promise.all(langs.map(lang => translate(phrase, { to: lang })))
  res.json([phrase, ...translations])
})

export default router;
