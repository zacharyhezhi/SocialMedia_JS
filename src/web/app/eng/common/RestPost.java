package web.app.eng.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

public class RestPost {
	
	public List<String> ExtractBullyingKeywords(String text) throws ClientProtocolException, IOException
	{
		HttpClient httpClient = new DefaultHttpClient();
		HttpPost httpPost = new HttpPost("http://d2dcrc.cse.unsw.edu.au:9091/ExtractionAPI-0.0.1-SNAPSHOT/rest/keyword");
		List<NameValuePair> listParameter = new ArrayList<>();
		listParameter.add(new BasicNameValuePair("sentence", text));
		httpPost.setEntity(new UrlEncodedFormEntity(listParameter));
		
		HttpResponse response = httpClient.execute(httpPost);
		if(response.getStatusLine().getStatusCode() != 200) {
			throw new RuntimeException("Fail: Http Error Code: " + response.getStatusLine().getStatusCode());
		}
		
		BufferedReader br = new BufferedReader(new InputStreamReader((response.getEntity().getContent())));
		String line, lines = "";
		while ((line = br.readLine()) != null) {
			lines += line;
		}
		int beginIndex = lines.indexOf("keyword") + 10;
		int endIndex = beginIndex + lines.substring(beginIndex).indexOf('"') - 1;
		
		httpClient.getConnectionManager().shutdown();
		
		String[] keywords = lines.substring(beginIndex, endIndex).split(",");
		
		String content = "abuse, abusive, acknowledge, acting out, actual, addictive, administration, "
				+ "adult, advocate, affect, afraid, aggravate, aggressive, alone, ambush, annoy, "
				+ "anonymous, antagonistic, antagonize, anxiety, apathetic, appropriate, arrogant, "
				+ "assault, attack, attentive, attitude, audience, authority, beat up, behavior, "
				+ "belittle, bias, blase, blindside, boorish, bother, braggart, bully, burden, bystander, "
				+ "callow, campus, capitulate, captious, case, cautious, challenge, charges, cheat, "
				+ "childhood, churlish, coaches, cold, communication, complain, complaint, compulsive, "
				+ "concern, confidence, conflict, consequence, console, consult, contemptible, control, "
				+ "corner, counselor, courage, covetous, creepy, crime, criminal, critical, crude, cruel, "
				+ "crying, culpable, curt, cyber-bullying, cynical, decency, deed, demeaning, dependent, "
				+ "depression, desensitize, despair, despot, destructive, devastate, devious, dictatorial, "
				+ "die, different, disaster, discourage, discussion, disdainful, dishonesty, dishonorable, "
				+ "disregard, disrespectful, dodge, dominate, effect, effort, egoist, egotism, elitist, "
				+ "elude, embarrassment, emotional, empathy, endure, epithet, escalation, evade, evil, "
				+ "exclusion, explosive, exposure, expulsion, extort, extravagant, extreme, extreme, "
				+ "failure, falseness, fanatic, favoritism, fear, fervid, flashback, focus, football, "
				+ "force, frailty, friendship, fright, frightened, frozen, gang, glib, gossip, grouchy, "
				+ "guilty, harassment, hard-hearted, haunted, hazing, head-off, healing, heedless, help, "
				+ "hidden, hide, hopeless, hostile, hounded, hurt, ignoble, ignorant, ignore, ill-tempered, "
				+ "impetuous, implacable, impolite, improper, imprudent, impudent, impunity, inappropriate, "
				+ "incidence, indecent, indecorous, indifference, ineffective, innocent, insight, insolent, "
				+ "insulting, intentional, intolerant, irascible, irresponsible, isolated, jaundiced, "
				+ "jealousy, judge, jumpy, justice, justify, juvenile, keen, kicked, kindness, knockdown, "
				+ "knowledge, knuckle, language, lethal, lifelong, litigation, loathsome, loss, malevolent, "
				+ "malicious, manipulative, marked, mean, meddler, medical, memories, merciless, mercurial, "
				+ "mercy, misbehavior, miserable, misery, mistrustful, momentum, monitor, nefarious, "
				+ "neglectful, negligent, nervous, neurotic, notorious, nuisance, obnoxious, obsequious, "
				+ "odious, offensive, oppressive, ostracize, out-of-line, outcast, outrageous, overwhelm, "
				+ "pain, painful, partial, passive, peace, peers, pervasive, petulant, physical, picked on, "
				+ "pity, police, popularity, prejudicial, preposterous, pretentious, prevention, prey on, "
				+ "problem, proceed, protection, protest, psychological, punched, punishment, pushing, "
				+ "put-down, quake, quandary, quarrelsome, querulous, quibbler, quirky, quiver, rancorous, "
				+ "reckless, report, representation, repugnance, reticent, rights, rude, ruthless, school, "
				+ "school, scornful, secrets, seek, selfish, sensitive, seriousness, shake, shame, shock, "
				+ "shouting, shoving, shunned, silence, skill, slur, socialize, solution, sour, spit on, "
				+ "sports, stern, struggle, suffering, suicidal, sullen, support, surly, survivor, "
				+ "suspect, suspension, suspicious, target, taunt, tears, tease, temper, terrify, testy, "
				+ "thoughtless, thug, thuggish, tolerance, tolerate, torment, tormentor, trauma, "
				+ "treacherous, treatment, trick, trust, uncivil, uncouth, unethical, unfair, unify, "
				+ "unmannerly, unreasonable, unrefined, unrelenting, unsavory, unworthy, verbal abuse, "
				+ "vicious, victim, vigilance, vile, villainous, violence, violent, volatile, warning, "
				+ "wary, waspish, watchful, weakness, weary, welfare, whine, why, wicked, wishy-washy, "
				+ "words, worry, wound, wrath, wrong, yelling, youngsters, youth, zealot";
		String[] bullyingKeywords = content.split(", ");
		
		List<String> detectedKeywords = new ArrayList<String>();
		for (String keyword : keywords) {
			for (String bullyingKeyword : bullyingKeywords) {
				if (keyword.length() == bullyingKeyword.length() && keyword.toLowerCase().equals(bullyingKeyword)) {
					detectedKeywords.add(keyword);
					break;
				}
			}
		}
		
		return detectedKeywords;
	}
	
}
