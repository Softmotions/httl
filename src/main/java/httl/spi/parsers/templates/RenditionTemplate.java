package httl.spi.parsers.templates;

import httl.Context;
import httl.Template;
import httl.spi.Listener;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import java.text.ParseException;
import java.util.Map;

public class RenditionTemplate extends TemplateWrapper {

	private final Listener rendition;

	public RenditionTemplate(Template template, Listener rendition) {
		super(template);
		this.rendition = rendition;
	}

	@Override
	public void render(Map<String, Object> parameters, OutputStream stream)
			throws IOException, ParseException {
		if (Context.getContext().getOut() != stream) {
			Context.pushContext(this, parameters, stream);
			try {
				rendition.render(Context.getContext());
			} finally {
				Context.popContext();
			}
		} else {
			rendition.render(Context.getContext());
		}
	}

	@Override
	public void render(Map<String, Object> parameters, Writer writer)
			throws IOException, ParseException {
		if (Context.getContext().getOut() != writer) {
			Context.pushContext(this, parameters, writer);
			try {
				rendition.render(Context.getContext());
			} finally {
				Context.popContext();
			}
		} else {
			rendition.render(Context.getContext());
		}
	}

}